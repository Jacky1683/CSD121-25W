package lab2;

import javax.imageio.ImageIO;
//   " javax.imageio.ImageIO " Provides classes to read and write images in various formats

import javax.swing.*;
// " javax.swing " is part of the Java standard library, and it is used for building graphical user interfaces (GUIs).

import java.awt.*;
// " java.awt "  Provides classes for graphics and GUI elements like colors, layouts, and images.

import java.io.IOException;
// " java.io.I0Exception "  Handles exceptions that occur during input or output operations like reading from a file or network.

import java.io.InputStream;
// " java.io.InputStream "  Represents an input stream of bytes used for reading data from various sources.

import java.net.URI;
// " java.net.URI "  Represents a Uniform Resource Identifier (URI), used to handle and manipulate URIs (such as URLs).

import java.net.http.HttpClient;
//  " java.net.http.HttpClient "  Create and send the HTTP request using HttpClient

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
// " java.net.http "   Used to make HTTP requests and handle responses.

public class AvatarGenerator {

    public static void main(String[] args) {

        try {
            var avatarStream = AvatarGenerator.getRandomAvatarStream();
            // " AvatarGenerator.getRandomAvatarStream() "  This method generates a random avatar using the Dicebear API and returns the image as an InputStream.

            AvatarGenerator.showAvatar(avatarStream);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static InputStream getRandomAvatarStream() throws IOException, InterruptedException {
        // " getRandomAvatarStream " is a static method that returns an InputStream. It generates a random avatar.


        String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
        // " styles " is a reference variable of type String array.
        var style = styles[(int)(Math.random() * styles.length)];
        // style, Seed, String and int is a Primitive types.

        // Generate a random seed for the avatar.
        var seed = (int)(Math.random() * 10000);

        // Create an HTTP request for a random avatar
        //  uri: is a Reference type.
        var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed));


        // HttpResponse is a Reference type.
        var request = HttpRequest.newBuilder(uri).build();
        try (var client = HttpClient.newHttpClient()) {
            //client: is a Reference type.

            var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            // " client Type " : HttpClient, Reference type.
            return response.body();
        }
    }

    public static void showAvatar(InputStream imageStream)
    // " showAvatar method argument " The imageStream is a reference type, InputStream.


    {
        JFrame frame = new JFrame("PNG Viewer");
        //  This creates a new JFrame with the title "PNG Viewer".
        //  The constructor initializes the frame and its properties like size and close operation.

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(200, 200);
        frame.getContentPane().setBackground(Color.BLACK);

        try {
            // Load the PNG image
            Image image = ImageIO.read(imageStream);

            // Create a JLabel to display the image
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            frame.add(imageLabel, BorderLayout.CENTER);

        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.setVisible(true);
    }
}




// Ques-3 Ans : Constructor Methods: There are no explicit constructors in the AvatarGenerator class.
// Therefore, the default constructor (implied constructor) is used, which is provided automatically by Java when no constructor is defined. The default constructor does nothing.
