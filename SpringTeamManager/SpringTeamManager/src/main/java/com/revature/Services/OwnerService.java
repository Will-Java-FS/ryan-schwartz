package com.revature.Services;

import com.revature.Model.Owner;

public interface OwnerService {
    // Trivial Services
    public Owner addConnection(Owner own);
    public Owner getByIds(int uid, int tid);
    public Owner getById(int id);
    public boolean deleteConnection(int id);
    public boolean deleteByUserTeamId(int uid, int tid);
}