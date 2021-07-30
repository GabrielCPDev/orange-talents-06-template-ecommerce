package com.zupedu.gabriel.mercadolivre.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_ranking")
public class Ranking implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Campo obrigatório!")
	private Long idCompra;
	@NotNull(message = "Campo obrigatório!")
	private Long idDonoDoProduto;

	public Ranking() {

	}

	public Ranking(Long id, Long idCompra, Long idDonoDoProduto) {
		super();
		this.id = id;
		this.idCompra = idCompra;
		this.idDonoDoProduto = idDonoDoProduto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	

	public Long getIdDonoDoProduto() {
		return idDonoDoProduto;
	}

	public void setIdDonoDoProduto(Long idDonoDoProduto) {
		this.idDonoDoProduto = idDonoDoProduto;
	}


	@Override
	public String toString() {
		return "Ranking [id=" + id + ", idCompra=" + idCompra + ", idDonoDoProduto=" + idDonoDoProduto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ranking other = (Ranking) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
