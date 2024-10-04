# Taller Cinco Crud
## Funcion.

```
create or replace function asignar_ranking (
c_grade in varchar)
return VARCHAR2
is
c_rank varchar2(20);
begin
    case c_grade
        when 'A' then
            c_rank := 'Excellent';
        when 'B' then
            c_rank := 'Very Good';
        when 'C' then
            c_rank := 'Good';
        when 'D' then
            c_rank := 'Fair';
        when 'F' then
            c_rank := 'Poor';
    else
        c_rank := 'No such grade';
        end case;
    return c_rank;
end;
```
# Taller 6 Trabajemos en clase con Oracle y Spring Boot

## Funcion Factorial en sql developer
Para hacer el llamdo por el postman al sprintools es 
GET: http://localhost:8080/Factorial/5
```
CREATE OR REPLACE FUNCTION Factorial( num NUMBER)
RETURN NUMBER
IS
BEGIN
    IF  num = 0 OR num = 1 THEN
        RETURN 1;
    ELSE 
        RETURN num *  Factorial(num -1);
    END IF;
END;
```

## Funcion calcularMCD en el sql developer
Para hacer el llamado por el postman es:
GET: http://localhost:8080/MCD?param1=48&param2=18
```
CREATE OR REPLACE FUNCTION calcularMCD(num1 NUMBER, num2 NUMBER) 
RETURN NUMBER IS
    a NUMBER := num1;
    b NUMBER := num2;
    temp NUMBER;
BEGIN
    IF a < 0 THEN
        a := -a;
    END IF;
    IF b < 0 THEN
        b := -b;
    END IF;

    WHILE b != 0 LOOP
        temp := b;
        b := a MOD b;
        a := temp;
    END LOOP;

    RETURN a;
END;
```

## Funcion Primos en el sql developer
Para hacer el llamado por el postman es:
GET: http://localhost:8080/Primo/5
```
CREATE OR REPLACE FUNCTION Primos ( num NUMBER) RETURN VARCHAR
IS
BEGIN
    IF num = 1 THEN
        return 'No es primo';
    END IF;
    for i in 2..FLOOR(SQRT(num))
        LOOP
            IF MOD(num,i)= 0 THEN
                return 'No es Primo';
            END IF;
        END LOOP;
    RETURN 'Es primo';
END;
```
## Funcion serieFibo en el sql developer
Para hacer el llamado por el postman es:
GET: http://localhost:8080/Fibo/5
```
CREATE OR REPLACE FUNCTION serieFibo ( num NUMBER) RETURN VARCHAR
IS
anterior NUMBER :=0;
siguiente NUMBER :=1;
suma NUMBER :=0;
resultado VARCHAR(4000) := TO_CHAR(anterior) || ', '|| TO_CHAR(siguiente);
BEGIN
    IF num = 1 THEN
        RETURN TO_CHAR(anterior);
    ELSIF num = 2 THEN
        RETURN resultado;
    END IF;
    
    FOR i in 3..num
        LOOP
            suma := anterior + siguiente;
            anterior := siguiente;
            siguiente := suma;
            resultado := resultado ||', '||TO_CHAR(suma);
        END LOOP;
    RETURN resultado;
END;
```
## Procedimiento actualizar_precio_producto en el sql developer
Para hacer el llamado por el postman es:
POST: http://localhost:8080/productos/actualizarPrecio?codProducto=P0001&nuevoPrecio=130.00
```
CREATE OR REPLACE PROCEDURE actualizar_precio_producto (
    p_cod_producto IN CHAR, 
    p_nuevo_precio IN NUMBER
) 
IS
BEGIN
    -- Validar que el nuevo precio no sea negativo
    IF p_nuevo_precio < 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'El precio no puede ser negativo.');
    END IF;

    -- Actualizar el precio del producto
    UPDATE productos
    SET precio_unitario = p_nuevo_precio
    WHERE cod_producto = p_cod_producto;

    -- Confirmar si la actualización fue exitosa
    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'El producto no existe.');
    END IF;
    
    COMMIT;
END;
```
