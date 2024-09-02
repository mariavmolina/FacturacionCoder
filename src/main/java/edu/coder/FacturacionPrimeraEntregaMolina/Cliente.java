package edu.coder.FacturacionPrimeraEntregaMolina;

import jakarta.persistence.*;

import java.util.Objects;

import java.util.List;

@Entity
@Table(name="CLIENTE")


public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name="APELLIDO")
    private String apellido;

    @Column (name = "DNI")
    private long dni;

    @OneToMany (mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Domicilio> domicilio;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, long dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public void addDomicilio(Domicilio domicilio) {
        domicilio.setCliente(this);
        this.domicilio.add(domicilio);
    }

    public void removeDomicilio(Domicilio domicilio) {
        domicilio.setCliente(null);
        this.domicilio.remove(domicilio);
    }


    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public List<Domicilio> getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(List<Domicilio> domicilio) {
        this.domicilio = domicilio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id && dni == cliente.dni && Objects.equals(nombre, cliente.nombre) && Objects.equals(apellido, cliente.apellido) && Objects.equals(domicilio, cliente.domicilio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, dni, domicilio);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "apellido='" + apellido + '\'' +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dni=" + dni +
                ", domicilio=" + domicilio +
                '}';
    }



}
