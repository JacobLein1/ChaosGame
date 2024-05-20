package view;

import controller.ChaosGameDescription;
import model.Vector2D;

public class CreateJuliaPage extends CreateFractal{

        public CreateJuliaPage() {
            setPageTitle("Create Julia transformation");
            setWidth(500);
            setHeight(600);
            setChaosGameDescription(
                    new ChaosGameDescription(
                            getMinCoords(),
                            getMaxCoords(),
                            getNewTransformations()
            ));

            setNewJuliaTransformationMenu();
        }
}
