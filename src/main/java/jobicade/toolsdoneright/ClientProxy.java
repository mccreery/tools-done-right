package jobicade.toolsdoneright;

public class ClientProxy extends CommonProxy {
    @Override
    public void onClient(Runnable runnable) {
        runnable.run();
    }
}
