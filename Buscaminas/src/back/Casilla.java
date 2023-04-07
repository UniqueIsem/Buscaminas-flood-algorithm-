package back;

public class Casilla {
    private int posFila;
    private int posColumna;
    private boolean mina;
    int numMinaAlrededor;
    boolean abierta;

    public Casilla(int fila, int columna) {
        this.posFila = fila;
        this.posColumna = columna;
    }
    
    public int getPosFila() {
        return posFila;
    }

    public void setPosFila(int posFila) {
        this.posFila = posFila;
    }

    public int getPosColumna() {
        return posColumna;
    }

    public void setPosColumna(int posColumna) {
        this.posColumna = posColumna;
    }

    public boolean isMina() {
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
    }

    public int getNumMinaAlrededor() {
        return numMinaAlrededor;
    }

    public void setNumMinaAlrededor(int numMinaAlrededor) {
        this.numMinaAlrededor = numMinaAlrededor;
    }
    
    public void incrementarNumeroMinasAlrededor(){
        this.numMinaAlrededor++;
    }

    public boolean isAbierta() {
        return abierta;
    }

    public void setAbierta(boolean abierta) {
        this.abierta = abierta;
    }
    
    
}
