package services;

import models.Claim;

import java.util.List;

public interface ClaimProcessManager {
    void add();
    void update(Claim claim);
    void delete(String claimId);
    Claim getOne(String claimId);
    void getAll();
}
