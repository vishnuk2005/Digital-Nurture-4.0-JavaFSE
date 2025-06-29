CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
IS
    v_interest_rate CONSTANT NUMBER := 0.01; -- 1% interest
BEGIN
    UPDATE Accounts
    SET Balance = Balance * (1 + v_interest_rate),
        LastModified = SYSDATE
    WHERE AccountType = 'Savings';

    DBMS_OUTPUT.PUT_LINE('Monthly interest processed for all savings accounts.');
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error processing monthly interest: ' || SQLERRM);
END;
/


 EXEC ProcessMonthlyInterest;
 