package ssv.example.repository;

import ssv.example.domain.Tema;
import ssv.example.validation.Validator;

public class TemaRepository extends AbstractCRUDRepository<String, Tema> {
    public TemaRepository(Validator<Tema> validator) {
        super(validator);
    }
}

