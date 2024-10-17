package org.autorizador.core.application;

import org.autorizador.core.ApplicationTests;
import org.autorizador.core.entities.AccountEntitie;
import org.autorizador.core.entities.AccountRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SaveAccountApplicationTest extends ApplicationTests {

    @InjectMocks
    private SaveAccountApplication saveAccountApplication;
    @Mock
    private AccountRepo repo;

    @Test
    void saveAccount() {
        var activeCard = true;
        var availableLimit = 1000;

        when(repo.save(any(AccountEntitie.class))).thenReturn(new AccountEntitie("id", activeCard, availableLimit));

        var id = saveAccountApplication.saveAccount(activeCard, availableLimit);

        assertNotNull(id);
        assertEquals(32, id.length());

        verify(repo, times(1)).save(any(AccountEntitie.class));
    }
}