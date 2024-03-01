package com.prueba.dev.domain.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tbl_tarjeta")
public class Tarjeta {

    @Id
	private long id_tarjeta;
	@Column
	private long numero;
	@Column
	private String titular;
	@Column
	private Date creada;
	@Column
	private Date vence;
	@Column
	private boolean activa;
	@Column
	private boolean bloqueada;
	@Column
	private long saldo;

	public long getId_tarjeta() {
		return id_tarjeta;
	}

	public void setId_tarjeta(long id_tarjeta) {
		this.id_tarjeta = id_tarjeta;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Date getCreada() {
		return creada;
	}

	public void setCreada(Date creada) {
		this.creada = creada;
	}

	public Date getVence() {
		return vence;
	}

	public void setVence(Date vence) {
		this.vence = vence;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public boolean isBloqueada() {
		return bloqueada;
	}

	public void setBloqueada(boolean bloqueada) {
		this.bloqueada = bloqueada;
	}

	public long getSaldo() {
		return saldo;
	}

	public void setSaldo(long saldo) {
		this.saldo = saldo;
	}

	public Tarjeta() {
		super();
	}

}
