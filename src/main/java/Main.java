public class Main {

    public static void main(String[] args) throws Exception {
        /*Vector2D minCoords = new Vector2D(0, 0);
        Vector2D maxCoords = new Vector2D(100, 100);
        Matrix2x2 matrix = new Matrix2x2(0, 0, 0, 0);
        Transform2D affineTransform2D = new AffineTransform2D(matrix, maxCoords);
        ArrayList<Transform2D> transforms = new ArrayList<>();
        transforms.add(affineTransform2D);

        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(minCoords, maxCoords, transforms);
        ChaosGame chaosGame = new ChaosGame(chaosGameDescription, 100, 100);*/

        /**ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGame chaosGame = chaosGameFileHandler.readTransformationFile("src/main/java/resources/affineTransformation.txt");
        chaosGame.runSteps(10000);

        ChaosCanvas canvas = chaosGame.getCanvas();
        for (int i = 0; i < chaosGame.getCanvas().getCanvasArray().length; i++) {
            String result = "";
            for (int j = 0; j < chaosGame.getCanvas().getCanvasArray()[i].length; j++) {
                if (chaosGame.getCanvas().getCanvasArray()[i][j] == 1) {
                    result = result + "*";
                } else {
                    result = result + " ";
                }
            }
            System.out.println(result);

        }
        //System.out.println(result.toString());
        Scanner input = new Scanner(System.in);

        System.out.println("Enter transformation type (Affine2D or Julia): ");
        String transformationName = input.nextLine();

        System.out.println("Enter the lower left coordinates: ");
        String minCoords = input.nextLine();
        System.out.println("Enter the upper right coordinates: ");
        String maxCoords = input.nextLine();
        int[] coords = new int[4];
        String[] minList = minCoords.split(",");
        String[] maxList = maxCoords.split(",");
        coords[0] = Integer.parseInt(minList[0].trim());
        coords[1] = Integer.parseInt(minList[1].trim());
        coords[2] = Integer.parseInt(maxList[0].trim());
        coords[3] = Integer.parseInt(maxList[1].trim());

        List<Transform2D> transformations = new ArrayList<>();
        if(transformationName.equals("Affine2D")){
            System.out.println("Enter transformation (a00, a01, a10, a11, b1, b2), enter 0 to when you have added all transformations: ");
            String transformation = input.nextLine();
            while (!transformation.equals("0")){
                System.out.println("Enter transformation (a00, a01, a10, a11, b1, b2), enter 0 to when you have added all transformations: ");
                transformation = input.nextLine();
                String[] values = transformation.split(",");
                transformations.add(new AffineTransform2D(new Matrix2x2(Integer.parseInt(values[0].trim()), Integer.parseInt(values[1].trim()), Integer.parseInt(values[2].trim()), Integer.parseInt(values[3].trim())), new Vector2D(Integer.parseInt(values[4].trim()), Integer.parseInt(values[5].trim()))));
            }
        }
        if(transformationName.equals("Julia")){
            System.out.println("Enter complex number c (real part, imaginary part): ");
            String transformation = input.nextLine();
            String[] values = transformation.split(",");
            Random rand = new Random();
            boolean sign = rand.nextBoolean();
            transformations.add(new JuliaTransform(new Complex(Integer.parseInt(values[0].trim()), Integer.parseInt(values[1].trim())), sign ? 1:-1));
        }

        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(coords[0], coords[1]), new Vector2D(coords[2], coords[3]), transformations);
        */

        /**
        ChaosGame chaosGame = new ChaosGame(ChaosGameDescriptionFactory.get("Barnsley"), 100, 100);
        chaosGame.runSteps(10000);
        //It works 😄
        //ChaosGame chaosGame = new ChaosGame(model.ChaosGameDescriptionFactory.barnsley(), 100, 100);
        //chaosGame.runSteps(10000);

        for (int i = 0; i < chaosGame.getCanvas().getCanvasArray().length; i++) {
            String result = "";
            for (int j = 0; j < chaosGame.getCanvas().getCanvasArray()[i].length; j++) {
                if (chaosGame.getCanvas().getCanvasArray()[i][j] == 1) {
                    result = result + "*";
                } else {
                    result = result + " ";
                }
            }
            System.out.println(result);

        }*/

    }



}