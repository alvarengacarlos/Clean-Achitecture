package alvarengacarlos.entity;

import java.math.BigDecimal;

public class CustomerImpl implements Customer {
    public Byte age;
    public String cpf;
    public String name;
    public BigDecimal income;
    public String location;

    public CustomerImpl(Byte age, String cpf, String name, BigDecimal income, String location) {
        this.age = age;
        this.cpf = cpf;
        this.name = name;
        this.income = income;
        this.location = location;
    }

    @Override
    public Byte getAge() {
        return this.age;
    }

    @Override
    public String getCpf() {
        return this.cpf;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public BigDecimal getIncome() {
        return this.income;
    }

    @Override
    public String getLocation() {
        return this.location;
    }
}
