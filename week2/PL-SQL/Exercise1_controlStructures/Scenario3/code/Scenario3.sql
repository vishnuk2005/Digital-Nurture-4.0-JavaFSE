DECLARE
    CURSOR c_loans_due IS
        SELECT l.LoanID, c.Name AS CustomerName, l.EndDate
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND (SYSDATE + 30); -- Loans due in next 30 days
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Loan Reminders (Loans due in next 30 days) ---');
    FOR loan_rec IN c_loans_due LOOP
        DBMS_OUTPUT.PUT_LINE('REMINDER: Dear ' || loan_rec.CustomerName || 
                             ', your loan (ID: ' || loan_rec.LoanID || 
                             ') is due on ' || TO_CHAR(loan_rec.EndDate, 'YYYY-MM-DD') || 
                             '. Please make the necessary arrangements.');
    END LOOP;
    IF NOT c_loans_due%ISOPEN THEN -- Check if any loans were found
        DBMS_OUTPUT.PUT_LINE('No loans are due within the next 30 days.');
    END IF;
    DBMS_OUTPUT.PUT_LINE('--- End of Reminders ---');
END;
/
