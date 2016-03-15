package mx.gob.hidalgo.repss.planeacion.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by kkimvazquezangeles on 11/05/15.
 */
@Entity
public class Archivo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="archivo_id_seq")
    @SequenceGenerator(name="archivo_id_seq", sequenceName="archivo_id_seq")
    private Long id;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "depto_id", nullable = false)
    private Departamento departamento;
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
