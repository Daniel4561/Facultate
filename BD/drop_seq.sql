BEGIN
  FOR i IN (SELECT us.sequence_name
              FROM USER_SEQUENCES us) LOOP
    EXECUTE IMMEDIATE 'drop sequence '|| i.sequence_name ||'';
  END LOOP;
END;

drop table angajat;
drop table locatii;
drop table joburi;
drop table departamente;