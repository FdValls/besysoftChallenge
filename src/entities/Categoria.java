package entities;

public enum Categoria {

    MUJER("mujer"),
    INFANTIL("infantil"),
    HOMBRE("hombre");

    private String descripcion;

    private Categoria(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
