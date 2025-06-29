CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department IN Employees.Department%TYPE,
    p_bonus_percentage IN NUMBER
)
IS
BEGIN
    IF p_bonus_percentage < 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Bonus percentage cannot be negative.');
    END IF;

    UPDATE Employees
    SET Salary = Salary * (1 + (p_bonus_percentage / 100))
    WHERE Department = p_department;

    DBMS_OUTPUT.PUT_LINE('Salaries updated for employees in ' || p_department || ' department with a ' || p_bonus_percentage || '% bonus.');
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating employee bonus: ' || SQLERRM);
END;
/

 EXEC UpdateEmployeeBonus('IT', 5);
 