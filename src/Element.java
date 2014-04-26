public abstract class Element {
    protected String name;
    protected Element childOf;

    // Constructor.
    public Element(String string) {
        this.name = string;
        childOf = null;
    }

    public void showTree() {
        showTreePrint(this);
    }

    // Draws tree (starting from used branch).
    static int treeLevel = 0;
    protected static void showTreePrint(Element e) {
        for (int i = 0; i < treeLevel; i++) {
            System.out.print("   ");
        }
        System.out.println(e.getName() + " [" + treeLevel + "]");
    }

    // Return parent id.
    Element isChildOf() {
        return childOf;
    }

    // Return name.
    String getName() {
        return "<" + name + ">";
    }

    // Shows path to root.
    public void showPathToRoot() {
        System.out.print(this.getName() + " is child");

        // Start recursion.
        pRootPath();
    }

    // (Private) Recursion path func.
    private void pRootPath() {
        if (this.childOf != null) {
            System.out.print(" of " + this.childOf.getName());
            this.childOf.pRootPath();
        }
    }

}
