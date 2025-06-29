CREATE SEQUENCE SEQ_TRANSACTION_ID START WITH 100 INCREMENT BY 1;
 CREATE OR REPLACE PROCEDURE TransferFunds (
    p_source_account_id IN Accounts.AccountID%TYPE,
    p_destination_account_id IN Accounts.AccountID%TYPE,
    p_amount IN NUMBER
)
IS
    v_source_balance Accounts.Balance%TYPE;
BEGIN
    -- Check if amount is positive
    IF p_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Transfer amount must be positive.');
    END IF;

    -- Get source account balance
    SELECT Balance INTO v_source_balance
    FROM Accounts
    WHERE AccountID = p_source_account_id
    FOR UPDATE; -- Lock the row to prevent concurrent updates

    -- Check for sufficient funds
    IF v_source_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20003, 'Insufficient funds in source account (ID: ' || p_source_account_id || ').');
    END IF;

    -- Deduct from source account
    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_source_account_id;

    -- Add to destination account
    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_destination_account_id;

    -- Record transactions
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (SEQ_TRANSACTION_ID.NEXTVAL, p_source_account_id, SYSDATE, p_amount, 'TransferOut');

    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (SEQ_TRANSACTION_ID.NEXTVAL, p_destination_account_id, SYSDATE, p_amount, 'TransferIn');

    DBMS_OUTPUT.PUT_LINE('Successfully transferred ' || p_amount || ' from Account ' || p_source_account_id || ' to Account ' || p_destination_account_id || '.');
    COMMIT;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: One of the account IDs does not exist.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error transferring funds: ' || SQLERRM);
END;
/
 EXEC TransferFunds(2, 1, 500);