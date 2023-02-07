public class ShortWordFilter implements Filter {
    public boolean accept(Object obj) {
        return (obj instanceof String && ((String) obj).length( ) < 5);
    }
}