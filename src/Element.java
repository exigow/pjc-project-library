public class Element {
    private String name;
    protected Element childOf;

    public Element(String string) {
        this.name = string;
        childOf = null;
    }

    public void showTree() {
        showTreePrint(this);
    }

    public static int treeLevel = 0;
    public static void showTreePrint(Element e) {
        for (int i = 0; i < treeLevel; i++) {
            System.out.print("   ");
        }
        System.out.println(e.name + " [" + treeLevel + "]");
    }
}
