import javax.swing.*;
import java.awt.*;

public class FlowerShopGUI extends JFrame {

    JComboBox<String> flowerBox;
    JTextField quantityField;
    JLabel totalLabel;

    SupabaseService service = new SupabaseService();

    public FlowerShopGUI() {

        setTitle("🌸 Blossom Flower Shop");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        String[] flowers = {"Rose 🌹", "Tulip 🌷", "Sunflower 🌻", "Daisy 🌼"};

        flowerBox = new JComboBox<>(flowers);
        quantityField = new JTextField(5);
        totalLabel = new JLabel("Total: 0");

        JButton orderButton = new JButton("Order 🌸");

        add(new JLabel("Choose Flower:"));
        add(flowerBox);

        add(new JLabel("Quantity:"));
        add(quantityField);

        add(orderButton);
        add(totalLabel);

        orderButton.addActionListener(e -> placeOrder());
    }

    void placeOrder() {

        String flower = (String) flowerBox.getSelectedItem();
        int qty = Integer.parseInt(quantityField.getText());

        int price = switch (flower) {
            case "Rose 🌹" -> 50;
            case "Tulip 🌷" -> 40;
            case "Sunflower 🌻" -> 60;
            default -> 30;
        };

        int total = price * qty;

        totalLabel.setText("Total: " + total);

        service.addOrder(flower, qty, total);
    }
}