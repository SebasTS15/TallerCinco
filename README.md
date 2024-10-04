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
