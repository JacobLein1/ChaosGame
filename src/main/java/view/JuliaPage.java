package view;

import controller.ChaosGameDescriptionFactory;

public class JuliaPage extends Fractal {

    public JuliaPage() {
        setPageTitle("Julia");
        setWidth(500);
        setHeight(600);
        setChaosGameDescription(ChaosGameDescriptionFactory.get("Julia"));

        setMenu();
        showFractalOnAction();
        setJuliaCoord();
    }
}
