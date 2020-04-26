package com.algaworks.osworks.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

import com.algaworks.osworks.domain.ValidationGroups;
import com.algaworks.osworks.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

@Entity
public class OrdemServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @Valid
	// @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	// @NotNull(groups = {ValidationGroups.ClienteId.class})
	@ManyToOne
	private Cliente cliente;

	// @NotBlank
	private String descricao;

	// @NotNull
	private BigDecimal preco;

	@Enumerated(EnumType.STRING)
	// @JsonProperty(access = Access.READ_ONLY)
	private StatusOrdemServico status;

	// @JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime data_abertura;

	// @JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime data_finalizacao;

	@OneToMany(mappedBy = "ordemServico")
	private List<Comentario> comentarios = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public StatusOrdemServico getStatus() {
		return status;
	}

	public void setStatus(StatusOrdemServico status) {
		this.status = status;
	}

	public OffsetDateTime getData_abertura() {
		return data_abertura;
	}

	public void setData_abertura(OffsetDateTime data_abertura) {
		this.data_abertura = data_abertura;
	}

	public OffsetDateTime getData_finalizacao() {
		return data_finalizacao;
	}

	public void setData_finalizacao(OffsetDateTime data_finalizacao) {
		this.data_finalizacao = data_finalizacao;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
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
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean nãoPodeSerFinalizada() {
		return !podeSerFinalizada();
	}

	public boolean podeSerFinalizada() {

		return StatusOrdemServico.ABERTA.equals(getStatus());

	}

	public void finalizar() {

		if(nãoPodeSerFinalizada()) {
			
			throw new NegocioException("Ordem de serviço não pode ser finalizada!");
		}
		
		setStatus(StatusOrdemServico.FINALIZADA);
		setData_finalizacao(OffsetDateTime.now());
	}

}
