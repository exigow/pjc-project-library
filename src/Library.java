public class Library {
    Container libContainer;

    public Library() {
        libContainer = new Container("lib");
        Container drobe0 = (Container)libContainer.addChild(new Container("Fizyka"));
            Container shelf0 = (Container)drobe0.addChild(new Container("Mechanika kwantowa"));
                shelf0.addChild(new Element("Wykłady z fizyki"));
                shelf0.addChild(new Element("Mechanika kwantowa: Wstęp"));
            Container shelf1 = (Container)drobe0.addChild(new Container("Elektronika"));
                shelf1.addChild(new Element("Układy analogowe"));
                shelf1.addChild(new Element("Zastosowanie diody LED"));
                shelf1.addChild(new Element("Wprowadzenie do elektroniki"));
        Container drobe1 = (Container)libContainer.addChild(new Container("Psychologia"));
            drobe1.addChild(new Element("Natura człowieka"));
            drobe1.addChild(new Element("Mentalizm"));
            drobe1.addChild(new Element("Techniki studiowania"));
        Container drobe2 = (Container)libContainer.addChild(new Container("Hobby"));
            drobe2.addChild(new Element("Komiks"));

        libContainer.showTree();
    }
}
