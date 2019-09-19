package com.company.JunghoonYoonU1Capstone.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class Console {
    private Integer console_id;
    @NotEmpty(message = "Supply a model")
    private String model;
    @NotEmpty(message = "Supply a manufacturer")
    private String manufacturer;
    @NotEmpty(message = "Supply a memory amount")
    private String memory_amount;
    @NotEmpty(message = "Supply a processor")
    private String processor;
    @NotNull(message = "Give a price")
    private BigDecimal price;
    @NotNull(message = "Provide a quantity")
    private Integer quantity;

    public Integer getConsole_id() {
        return console_id;
    }

    public void setConsole_id(Integer console_id) {
        this.console_id = console_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemory_amount() {
        return memory_amount;
    }

    public void setMemory_amount(String memory_amount) {
        this.memory_amount = memory_amount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Console console = (Console) o;
        return console_id.equals(console.console_id) &&
                model.equals(console.model) &&
                manufacturer.equals(console.manufacturer) &&
                Objects.equals(memory_amount, console.memory_amount) &&
                Objects.equals(processor, console.processor) &&
                price.equals(console.price) &&
                quantity.equals(console.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(console_id, model, manufacturer, memory_amount, processor, price, quantity);
    }
}
