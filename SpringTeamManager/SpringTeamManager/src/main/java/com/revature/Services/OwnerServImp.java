package com.revature.Services;
import com.revature.Model.Owner;
import com.revature.Model.Team;
import com.revature.Repos.OwnerRepository;
import com.revature.Repos.TeamsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public class OwnerServImp implements OwnerService{
    @Autowired
    OwnerRepository or;

    @Override
    public Owner addConnection(Owner own) {
        return or.save(own);
    }

    @Override
    public boolean deleteConnection(int id) {
        try {
            or.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteByUserTeamId(int uid, int tid) {
        List<Owner> result = or.findAllByIds(uid, tid);
        try {
        or.deleteAllInBatch(result);
        return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}