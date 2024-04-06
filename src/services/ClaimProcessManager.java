package services;

import models.Claim;

import java.util.List;

/**
 * RMIT University Vietnam - Assignment 1
 * @author <Min Chi Gia Khiem - S3878280>
 * @version 1.0
 * @since 04/05/2024
 */

public interface ClaimProcessManager {
    void add();
    void update();
    void delete();
    void getOne();
    void getAll();
}
