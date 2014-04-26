public class Library {
    Container libContainer;

    public Library() {
        libContainer = new Container("lib");
        /*
        Container drobe0 = (Container)libContainer.addChild(new Container("Fizyka"));
            Container shelf0 = (Container)drobe0.addChild(new Container("Mechanika kwantowa"));
                shelf0.addChild(new Book("Wykłady z fizyki"));
                shelf0.addChild(new Book("Mechanika kwantowa: Wstęp"));
            Container shelf1 = (Container)drobe0.addChild(new Container("Elektronika"));
                shelf1.addChild(new Book("Układy analogowe"));
                shelf1.addChild(new Book("Zastosowanie diody LED"));
                shelf1.addChild(new Book("Wprowadzenie do elektroniki"));
        Container drobe1 = (Container)libContainer.addChild(new Container("Psychologia"));
            Element test = drobe1.addChild(new Book("Natura człowieka"));
            drobe1.addChild(new Book("Mentalizm"));
            drobe1.addChild(new Book("Techniki studiowania"));
        Container drobe2 = (Container)libContainer.addChild(new Container("Hobby"));
            drobe2.addChild(new Book("Komiks"));
*/
        libContainer.showTree();

       // test.showPathToRoot();
    }
}
