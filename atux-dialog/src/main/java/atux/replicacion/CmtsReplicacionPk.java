package atux.replicacion;

import java.io.Serializable;

public class CmtsReplicacionPk implements Serializable {
	private String coCompania;
	private String coLocal;
	private String coReplicador;

	/** full constructor */
	public CmtsReplicacionPk(String coCompania, String coLocal, String coReplicador) {
		this.coCompania = coCompania;
		this.coLocal = coLocal;
		this.coReplicador = coReplicador;
	}

	public String getCoCompania() {
		return coCompania;
	}

	public void setCoCompania(String coCompania) {
		this.coCompania = coCompania;
	}

	public String getCoLocal() {
		return coLocal;
	}

	public void setCoLocal(String coLocal) {
		this.coLocal = coLocal;
	}

	public String getCoReplicador() {
		return coReplicador;
	}

	public void setCoReplicador(String coReplicador) {
		this.coReplicador = coReplicador;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		final CmtsReplicacionPk that = (CmtsReplicacionPk) o;

		if (coCompania != null ? !coCompania.equals(that.coCompania) : that.coCompania != null)
			return false;
		if (coLocal != null ? !coLocal.equals(that.coLocal) : that.coLocal != null)
			return false;
		if (coReplicador != null ? !coReplicador.equals(that.coReplicador) : that.coReplicador != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (coCompania != null ? coCompania.hashCode() : 0);
		result = 29 * result + (coLocal != null ? coLocal.hashCode() : 0);
		result = 29 * result + (coReplicador != null ? coReplicador.hashCode() : 0);
		return result;
	}
}
