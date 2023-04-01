package ssv.example.repository;

import ssv.example.domain.Nota;
import ssv.example.domain.Pair;
import ssv.example.validation.Validator;

public class NotaRepository extends AbstractCRUDRepository<Pair<String, String>, Nota> {
    public NotaRepository(Validator<Nota> validator) {
        super(validator);
    }
}
