package com.proyecto.proyectointegrador.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESPUESTAS_EVALUACION")
public class RespuestaEvaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @JsonProperty("id")
    private Long id;

    @Column(name = "PROYECTO_ID")
    @JsonProperty("proyectoId")
    private Long proyectoId;

    // --- DATOS PERSONALES ---
    @Column(name = "NOMBRE")
    @JsonProperty("nombre")
    private String nombre;

    @Column(name = "SEXO")
    @JsonProperty("sexo")
    private String sexo;

    @Column(name = "EDAD")
    @JsonProperty("edad")
    private Integer edad;

    @Column(name = "CORREO")
    @JsonProperty("correo")
    private String correo;

    // --- PREGUNTAS LIKERT (1 a 15) ---
    @Column(name = "P1")
    @JsonProperty("p1")
    private Integer p1;

    @Column(name = "P2")
    @JsonProperty("p2")
    private Integer p2;

    @Column(name = "P3")
    @JsonProperty("p3")
    private Integer p3;

    @Column(name = "P4")
    @JsonProperty("p4")
    private Integer p4;

    @Column(name = "P5")
    @JsonProperty("p5")
    private Integer p5;

    @Column(name = "P6")
    @JsonProperty("p6")
    private Integer p6;

    @Column(name = "P7")
    @JsonProperty("p7")
    private Integer p7;

    @Column(name = "P8")
    @JsonProperty("p8")
    private Integer p8;

    @Column(name = "P9")
    @JsonProperty("p9")
    private Integer p9;

    @Column(name = "P10")
    @JsonProperty("p10")
    private Integer p10;

    @Column(name = "P11")
    @JsonProperty("p11")
    private Integer p11;

    @Column(name = "P12")
    @JsonProperty("p12")
    private Integer p12;

    @Column(name = "P13")
    @JsonProperty("p13")
    private Integer p13;

    @Column(name = "P14")
    @JsonProperty("p14")
    private Integer p14;

    @Column(name = "P15")
    @JsonProperty("p15")
    private Integer p15;

    // --- ESCALAS SAM ---
    @Column(name = "VALENCIA")
    @JsonProperty("valencia")
    private Integer valencia;

    @Column(name = "ACTIVACION")
    @JsonProperty("activacion")
    private Integer activacion;

    @Column(name = "DOMINIO")
    @JsonProperty("dominio")
    private Integer dominio;

    // --- AUDIO EN BASE64 (Mapeado a CLOB de Oracle) ---
    @Lob
    @Column(name = "AUDIO_BASE64")
    @JsonProperty("audio_grabado_base64")
    private String audioGrabadoBase64;

    @Column(name = "FECHA_REGISTRO")
    @JsonProperty("fechaRegistro")
    private LocalDateTime fechaRegistro;

    public RespuestaEvaluacion() {}

    @PrePersist
    public void prePersist() {
        if (this.fechaRegistro == null) {
            this.fechaRegistro = LocalDateTime.now();
        }
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProyectoId() { return proyectoId; }
    public void setProyectoId(Long proyectoId) { this.proyectoId = proyectoId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public Integer getP1() { return p1; }
    public void setP1(Integer p1) { this.p1 = p1; }

    public Integer getP2() { return p2; }
    public void setP2(Integer p2) { this.p2 = p2; }

    public Integer getP3() { return p3; }
    public void setP3(Integer p3) { this.p3 = p3; }

    public Integer getP4() { return p4; }
    public void setP4(Integer p4) { this.p4 = p4; }

    public Integer getP5() { return p5; }
    public void setP5(Integer p5) { this.p5 = p5; }

    public Integer getP6() { return p6; }
    public void setP6(Integer p6) { this.p6 = p6; }

    public Integer getP7() { return p7; }
    public void setP7(Integer p7) { this.p7 = p7; }

    public Integer getP8() { return p8; }
    public void setP8(Integer p8) { this.p8 = p8; }

    public Integer getP9() { return p9; }
    public void setP9(Integer p9) { this.p9 = p9; }

    public Integer getP10() { return p10; }
    public void setP10(Integer p10) { this.p10 = p10; }

    public Integer getP11() { return p11; }
    public void setP11(Integer p11) { this.p11 = p11; }

    public Integer getP12() { return p12; }
    public void setP12(Integer p12) { this.p12 = p12; }

    public Integer getP13() { return p13; }
    public void setP13(Integer p13) { this.p13 = p13; }

    public Integer getP14() { return p14; }
    public void setP14(Integer p14) { this.p14 = p14; }

    public Integer getP15() { return p15; }
    public void setP15(Integer p15) { this.p15 = p15; }

    public Integer getValencia() { return valencia; }
    public void setValencia(Integer valencia) { this.valencia = valencia; }

    public Integer getActivacion() { return activacion; }
    public void setActivacion(Integer activacion) { this.activacion = activacion; }

    public Integer getDominio() { return dominio; }
    public void setDominio(Integer dominio) { this.dominio = dominio; }

    public String getAudioGrabadoBase64() { return audioGrabadoBase64; }
    public void setAudioGrabadoBase64(String audioGrabadoBase64) { this.audioGrabadoBase64 = audioGrabadoBase64; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}