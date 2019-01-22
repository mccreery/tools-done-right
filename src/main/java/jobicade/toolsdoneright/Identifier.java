package jobicade.toolsdoneright;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;

import org.apache.commons.lang3.StringUtils;

public class Identifier {
	private final ImmutableList<String> tokensLower;
	private final EnumMap<Format, String> formattedNames = new EnumMap<>(Format.class);

	public Identifier(List<String> tokens) { this(tokens.stream()); }
	public Identifier(String... tokens) { this(Arrays.stream(tokens)); }
	public Identifier(String formattedName) { this(splitTokens(formattedName)); }

	private Identifier(Stream<String> tokens) {
		this.tokensLower = tokens.map(String::toLowerCase).collect(ImmutableList.toImmutableList());
	}

	private static List<String> splitTokens(String formattedName) {
		String[] split = StringUtils.splitByCharacterTypeCamelCase(formattedName);
		return Arrays.stream(split).filter(ALLOWED_TOKENS).collect(Collectors.toList());
	}

	private static final Predicate<String> ALLOWED_TOKENS = Pattern.compile("^[A-Za-z0-9]+$").asPredicate();

	public List<String> getTokens() {
		return tokensLower;
	}

	public String format(Format format) {
		return formattedNames.computeIfAbsent(format, f -> f.apply(tokensLower));
	}

	public enum Format {
		LOWERCASE {
			@Override
			public void append(StringBuilder builder, int i, String word) {
				builder.append(word);
			}
		}, SNAKE {
			@Override
			public void append(StringBuilder builder, int i, String word) {
				if(i > 0) builder.append('_');
				builder.append(word);
			}
		}, UPPERCASE {
			@Override
			public void append(StringBuilder builder, int i, String word) {
				builder.append(word.toUpperCase());
			}
		}, CONSTANT {
			@Override
			public void append(StringBuilder builder, int i, String word) {
				if(i > 0) builder.append('_');
				builder.append(word.toUpperCase());
			}
		}, CAMELCASE {
			@Override
			public void append(StringBuilder builder, int i, String word) {
				if(word != null && !word.isEmpty()) {
					builder.append(Character.toUpperCase(word.charAt(0)));
					builder.append(word, 1, word.length());
				}
			}
		}, HEADLESS {
			@Override
			public void append(StringBuilder builder, int i, String word) {
				(i > 0 ? CAMELCASE : LOWERCASE).append(builder, i, word);
			}
		};

		protected abstract void append(StringBuilder builder, int i, String word);

		private final String apply(List<String> tokens) {
			StringBuilder builder = new StringBuilder();

			for(int i = 0; i < tokens.size(); i++) {
				append(builder, i, tokens.get(i));
			}
			return builder.toString();
		}
	}
}
