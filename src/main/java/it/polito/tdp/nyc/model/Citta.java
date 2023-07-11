package it.polito.tdp.nyc.model;

import com.javadocmd.simplelatlng.LatLng;

public class Citta {
    
    private String nome;
    private LatLng posizione;
    
    public Citta(String nome, LatLng posizione) {
   	 this.nome = nome;
   	 this.posizione = posizione;
    }

    public String getNome() {
   	 return nome;
    }

    public void setNome(String nome) {
   	 this.nome = nome;
    }

    public LatLng getPosizione() {
   	 return posizione;
    }

    public void setPosizione(LatLng posizione) {
   	 this.posizione = posizione;
    }

    @Override
    public int hashCode() {
   	 final int prime = 31;
   	 int result = 1;
   	 result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
   	 Citta other = (Citta) obj;
   	 if (nome == null) {
   		 if (other.nome != null)
   			 return false;
   	 } else if (!nome.equals(other.nome))
   		 return false;
   	 return true;
    }
}
