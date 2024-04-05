package services;

import models.Claim;

import java.util.List;

public interface ClaimProcessManager {
    void add();
    void update();
    void delete();
    void getOne();
    void getAll();
}
