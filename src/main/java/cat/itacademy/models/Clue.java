package cat.itacademy.models;

import cat.itacademy.exceptions.InvalidPriceException;
import cat.itacademy.exceptions.InvalidTextException;

public class Clue {

    private String name;
    private String theme;
    private String description;
    private double price;

    public Clue(String name, String theme, String description, double price) {
        this.name = requireText(name, "name");
        this.theme = requireText(theme, "theme");
        this.description = requireText(description, "description");
        this.price = normalizePrice(price);
    }

    private double normalizePrice(double price) {
        if (!Double.isFinite(price)) throw new InvalidPriceException("price must be finite");
        if (price < 0.0) throw new InvalidPriceException("price must be >= 0");
        return Math.round(price * 100.0) / 100.0;
    }

    private static String requireText(String text, String field) {
        if (text == null) throw new InvalidTextException(field + " is required");
        String trimmed = text.trim();
        if (trimmed.isEmpty()) throw new InvalidTextException(field + " is required");

        return trimmed;
    }

    public String getName() {
        return name;
    }

    public String getTheme() {
        return theme;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clue that = (Clue) o;
        return name != null && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
