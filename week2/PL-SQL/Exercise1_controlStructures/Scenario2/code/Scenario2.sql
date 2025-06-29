DECLARE
    CURSOR c_customers IS
        SELECT CustomerID, Balance, isVIP
        FROM Customers;
    
    v_customer_id Customers.CustomerID%TYPE;
    v_balance Customers.Balance%TYPE;
    v_is_vip Customers.isVIP%TYPE;
BEGIN
    FOR customer_rec IN c_customers LOOP
        IF customer_rec.Balance > 10000 THEN
            IF customer_rec.isVIP = 'F' THEN -- Only update if not already VIP
                UPDATE Customers
                SET isVIP = 'T'
                WHERE CustomerID = customer_rec.CustomerID;
                DBMS_OUTPUT.PUT_LINE('Customer ' || customer_rec.CustomerID || ' promoted to VIP status.');
            ELSE
                DBMS_OUTPUT.PUT_LINE('Customer ' || customer_rec.CustomerID || ' is already VIP.');
            END IF;
        ELSE
            IF customer_rec.isVIP = 'T' THEN -- Demote if balance drops below threshold
                UPDATE Customers
                SET isVIP = 'F'
                WHERE CustomerID = customer_rec.CustomerID;
                DBMS_OUTPUT.PUT_LINE('Customer ' || customer_rec.CustomerID || ' demoted from VIP status.');
            ELSE
                DBMS_OUTPUT.PUT_LINE('Customer ' || customer_rec.CustomerID || ' is not a VIP and balance is below threshold.');
            END IF;
        END IF;
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('VIP status updates complete.');
END;
/
