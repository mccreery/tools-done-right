package jobicade.toolsdoneright;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import com.google.common.collect.ImmutableList;

import net.minecraft.util.ResourceLocation;

public class Identifier extends ResourceLocation {
	private final List<String> tokens;
	private final Format format;

	public Identifier(String name) {
		this(name, null);
	}

	public Identifier(String name, Format format) {
		super(ToolsDoneRight.MODID, name);
		this.tokens = tokenize(name);
		this.format = format;
	}

	public Identifier(List<String> tokens, Format format) {
		super(ToolsDoneRight.MODID, format.apply(tokens));
		this.tokens = tokens;
		this.format = format;
	}

	private static final Pattern TOKENIZER = Pattern.compile(
		"[_-]|(?<!\\p{Lu}|[_-])(?=\\p{Lu})" + // Capital letters
		"|(?<!\\d|[_-])(?=\\d)" +             // Digits start
		"|(?<=\\d)(?!\\d)");                  // Digits end

	private static List<String> tokenize(String name) {
		if(name != null && !name.isEmpty()) {
			return ImmutableList.copyOf(TOKENIZER.split(name));
		} else {
			return Collections.emptyList();
		}
	}

	public List<String> getTokens() {
		return tokens;
	}

	public Format getFormat() {
		return format;
	}

	public Identifier withFormat(Format format) {
		if(format == this.format) {
			return this;
		} else {
			return new Identifier(format.apply(this), format);
		}
	}

	public enum Format {
		LOWERCASE {
			@Override
			public void append(StringBuilder builder, int i, String word) {
				builder.append(word.toLowerCase());
			}
		}, SNAKE {
			@Override
			public void append(StringBuilder builder, int i, String word) {
				if(i > 0) builder.append('_');
				LOWERCASE.append(builder, i, word);
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
				UPPERCASE.append(builder, i, word);
			}
		}, CAMELCASE {
			@Override
			public void append(StringBuilder builder, int i, String word) {
				if(word != null && !word.isEmpty()) {
					builder.append(Character.toUpperCase(word.charAt(0)));
					builder.append(word.toLowerCase(), 1, word.length());
				}
			}
		}, HEADLESS {
			@Override
			public void append(StringBuilder builder, int i, String word) {
				(i > 0 ? CAMELCASE : LOWERCASE).append(builder, i, word);
			}
		};

		protected abstract void append(StringBuilder builder, int i, String word);

		private final String apply(Identifier identifier) {
			return apply(identifier.tokens);
		}

		private final String apply(List<String> tokens) {
			StringBuilder builder = new StringBuilder();

			for(int i = 0; i < tokens.size(); i++) {
				append(builder, i, tokens.get(i));
			}
			return builder.toString();
		}
	}
}
