public class JuliaTransform implements Transform2D{
    private Complex point;
    private int sign;

    public JuliaTransform(Complex point, int sign){
        this.point = point;
        this.sign = sign;
    }

    public boolean checkSign(){
        if(sign == 1){
            return true;
        }
        if(sign == -1){
            return false;
        }
        else{
            throw new IllegalArgumentException("Sign must be 1 or -1");
        }
    }

    @Override
    public Vector2D Transform(Vector2D point) {
        Vector2D vector = point.subtract(this.point);
        Complex radicand = new Complex(vector.getX0(), vector.getX1());
        float sign = this.sign;
        return new Vector2D(radicand.sqrt().getX0()*sign, radicand.sqrt().getX1());
    }
}
