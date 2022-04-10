package com.example.easynoteapp.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotesRepositoryImpl implements NotesRepository{
    private static final NotesRepository INSTANCE = new NotesRepositoryImpl() ;

    public static NotesRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public ArrayList<Note> getNotes() {

        ArrayList<Note> notesList = new ArrayList<>();
        notesList.add(new Note(UUID.randomUUID().toString(),"first description","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur tristique libero porta nisl viverra ultrices. Proin interdum, magna eu convallis pretium, massa mauris porttitor ante, eget pretium ex magna fermentum erat. In dictum dignissim ligula at sodales. Quisque ullamcorper leo magna. Vivamus suscipit leo magna, eu cursus sem viverra in. Nunc sit amet nisl pharetra, laoreet erat pellentesque, consequat nisl. Nullam ac dolor eget mauris mattis imperdiet id ut ante. Nam elit tortor, suscipit a mattis sed, venenatis eu quam. Integer dignissim mi risus, at pretium nibh elementum ac. Curabitur convallis tincidunt massa, ac dictum erat blandit a. Vivamus consectetur gravida enim. Praesent id mauris consectetur dolor porttitor euismod eget quis diam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Aliquam auctor ex vel sem pellentesque, sit amet euismod lorem fermentum. Integer semper tempor nunc, ut luctus augue sagittis quis. "));
        notesList.add(new Note(UUID.randomUUID().toString(),"Second description","Vivamus eu odio euismod, congue velit at, gravida mi. Nam pulvinar scelerisque urna a gravida. Nunc scelerisque consectetur quam sed accumsan. Maecenas nec lacus a tellus cursus lobortis. Morbi sit amet consectetur augue, sit amet aliquam massa. Pellentesque metus massa, mattis quis consectetur vel, pharetra nec diam. Nulla a lectus eu purus lacinia pulvinar non ac lorem. "));
        notesList.add(new Note(UUID.randomUUID().toString(),"HGello description"," Donec fermentum purus vel faucibus dictum. Curabitur tristique, velit et ultrices suscipit, odio elit maximus nisi, quis rhoncus purus tellus vitae lectus. Integer aliquet, dui at tristique posuere, velit magna blandit erat, eu tempus nisi felis fermentum est. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed eu felis non odio pulvinar tristique. Donec pulvinar sapien quis nibh imperdiet, a vulputate quam efficitur."));
        notesList.add(new Note(UUID.randomUUID().toString(),"My description","ae neque nisi. Nam ultrices, lectus in tristique congue, erat leo venenatis lorem, vitae convallis elit tellus vel nulla. Integer in semper ante. Nullam et facilisis felis. Sed eleifend vitae ante at ullamcorper. Aenean eu vulputate risus, vitae vehicula ligula. Sed imperdiet quam mollis eros tincidunt, ut tincidunt elit pretium. Donec tincidunt nulla ut magna tempus, vel finibus neque ultrices. Morbi sollicitudin nunc in blandit imperdiet. Nulla aliquam velit quis nulla co"));
        notesList.add(new Note(UUID.randomUUID().toString(),"Last description","raesent ante urna, pretium consequat nisi vel, consectetur semper eros. Donec elementum, metus ultricies laoreet venenatis, augue nulla tincidunt massa, non blandit risus odio eget orci. Nulla facilisis auctor venenatis. Phasellus vel risus rutrum, pharetra dolor et, tristique nunc. Aenean eleifend eleifend velit, id g "));
        notesList.add(new Note(UUID.randomUUID().toString(),"Again description","Quisque ullamcorper leo magna. Vivamus suscipit leo magna, eu cursus sem viverra in. Nunc sit amet nisl pharetra, laoreet erat pellentesque, consequat nisl. Nullam ac dolor eget mauris mattis imperdiet id ut ante. Nam elit tortor, suscipit a mattis sed, venenatis eu quam. Integer dignissim mi risus, at pretium nibh elementum ac. Curabitur convallis tincidunt massa, ac dictum erat blandit a. Vivamus consectetur gravida enim. Praesent id mauris consectetur dolor porttitor euismod eget quis diam "));
        notesList.add(new Note(UUID.randomUUID().toString(),"Beginner description"," raesent ante urna, pretium consequat nisi vel, consectetur semper eros. Donec elementum, metus ultricies laoreet venenatis, augue nulla tincidunt massa, non blandit risus odio eget orci. Nulla facilisis auctor venenatis. Phasellus vel risus rutrum, pharetra dolor et, tristique nunc. Aenean eleifend eleifend velit, id g"));
        notesList.add(new Note(UUID.randomUUID().toString(),"FInish description","Morbi sit amet consectetur augue, sit amet aliquam massa. Pellentesque metus massa, mattis quis consectetur vel, pharetra nec diam. Nulla a lectus eu purus lacinia pulvinar non ac lorem. Pellentesque at egestas justo. Fusce nibh odio, egestas eu vulputate at, laoreet non nibh. Nam varius dictum tempus. Donec in ligula lectus."));
        notesList.add(new Note(UUID.randomUUID().toString(),"OK description","Cras pharetra orci orci, eget ultrices tellus convallis a. Praesent ullamcorper posuere leo sed mollis. Pellentesque sit amet congue velit. "));
        return notesList;
    }
}
