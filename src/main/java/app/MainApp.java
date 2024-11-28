package app;

import engine.math.Matrix4;
import engine.math.Vector3;
import engine.render.Renderer;
import engine.scene.Camera;
import engine.scene.Mesh;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class MainApp extends Application {
    private final List<Mesh> demoObjects = List.of(
            DemoObjects.createCube(),
            DemoObjects.createPyramid(),
            DemoObjects.createSquare(),
            DemoObjects.createCircle(32),
            DemoObjects.createSphere(16, 16)
    );

    Camera camera = new Camera(new Vector3(0, 0, -5), 60, 800.0 / 600.0, 0.1, 100.0);
    Matrix4 projection = camera.getProjectionMatrix();
    private boolean isSequential = true; // Toggle rendering mode
    private int currentIndex = 0;
    private double rotationAngle = 0.0;

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Renderer renderer = new Renderer();

        // Buttons to switch between rendering modes
        Button sequentialButton = new Button("Sequential Rendering");
        Button simultaneousButton = new Button("Simultaneous Rendering");

        sequentialButton.setOnAction(e -> isSequential = true);
        simultaneousButton.setOnAction(e -> isSequential = false);

        // Layout for buttons
        HBox buttonBox = new HBox(10, sequentialButton, simultaneousButton);
        BorderPane root = new BorderPane();
        root.setTop(buttonBox);
        root.setCenter(canvas);

        // Animation loop
        new javafx.animation.AnimationTimer() {
            @Override
            public void handle(long now) {
                // Clear the canvas with a dark background
                gc.setFill(Color.BLACK); // Set background color
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                if (isSequential) {
                    renderSequential(gc, renderer);
                } else {
                    renderSimultaneous(gc, renderer);
                }

                rotationAngle += 0.02;
            }
        }.start();

        Scene scene = new Scene(root, 800, 600, Color.BLACK);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo Objects Renderer");
        primaryStage.show();
    }

    private void renderSequential(GraphicsContext gc, Renderer renderer) {
        // Render one object at a time
        Mesh currentObject = demoObjects.get(currentIndex);

        // Apply rotation transformation
        Matrix4 rotation = Matrix4.rotationY(rotationAngle)
                .multiply(Matrix4.rotationX(rotationAngle / 2));

        // Set line color
        gc.setStroke(Color.WHITE);

        // Render the object
        renderer.render3D(gc, currentObject, rotation, projection, (int) gc.getCanvas().getWidth(), (int) gc.getCanvas().getHeight());

        // Switch to the next object every 5 seconds
        if (rotationAngle > Math.PI * 2) {
            rotationAngle = 0.0;
            currentIndex = (currentIndex + 1) % demoObjects.size();
        }
    }

    private void renderSimultaneous(GraphicsContext gc, Renderer renderer) {
        int index = 0;
        int rows = 2; // Number of rows for layout
        int cols = 3; // Number of columns for layout

        // Calculate spacing between objects
        double xSpacing = 3.0; // Horizontal spacing
        double ySpacing = 3.0; // Vertical spacing

        for (Mesh object : demoObjects) {
            // Calculate grid position
            int row = index / cols;
            int col = index % cols;

            // Offset each object in grid layout
            double offsetX = (col - (cols / 2.0)) * xSpacing; // Center horizontally
            double offsetY = (row - (rows / 2.0)) * ySpacing; // Center vertically
            double offsetZ = -10.0; // Move all objects slightly back

            // Apply transformations
            Matrix4 transformation = Matrix4.translation(offsetX, offsetY, offsetZ) // Spread objects
                    .multiply(Matrix4.rotationY(rotationAngle)) // Add rotation
                    .multiply(Matrix4.rotationX(rotationAngle / 2))
                    .multiply(Matrix4.scaling(0.7, 0.7, 0.7)); // Scale down to fit

            // Render each object
            renderer.render3D(gc, object, transformation, projection, (int) gc.getCanvas().getWidth(), (int) gc.getCanvas().getHeight());

            index++;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
