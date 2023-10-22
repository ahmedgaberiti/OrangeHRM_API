package resources;

import pojoClasses.AddCandidateApiPayload;
import pojoClasses.deletePayload;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {


    public AddCandidateApiPayload addCandidatePayload(String firstname, String lastname , String email){
        AddCandidateApiPayload  candidate=new AddCandidateApiPayload();
        candidate.setFirstName(firstname);
        candidate.setMiddleName("middleName");
        candidate.setLastName(lastname);
        candidate.setEmail(email);
        candidate.setComment("comment");
        candidate.setKeywords("keywords");
        candidate.setDateOfApplication("20/10/2023");
        candidate.setContactNumber("000000");
        candidate.setConsentToKeepData(false);
        return  candidate;

    }

    public deletePayload  deletePayloadBody (int id){

        deletePayload  d=new deletePayload();
        List<Integer> mylist=new ArrayList<Integer>();
        mylist.add(id);
        d.setIds(mylist);
        return d;

    }


}
