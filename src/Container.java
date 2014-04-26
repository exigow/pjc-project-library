import java.util.ArrayList;
import java.util.List;

public class Container extends Element {
    List<Element> contain;

    public Container(String string) {
        super(string);
        contain = new ArrayList<Element>();
    }

    // Add child to list.
    public Element addChild(Element element) {
        contain.add(element);
        element.childOf = this;
        return element;
    }

    @Override
    public void showTree() {
        showTreePrint(this);
        treeLevel++;
        for (Element element: contain) {
            element.showTree();
        }
        treeLevel--;
    }

}
