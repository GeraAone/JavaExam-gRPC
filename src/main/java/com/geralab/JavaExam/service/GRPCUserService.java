package com.geralab.JavaExam.service;

import com.geralab.JavaExam.*;
import com.geralab.JavaExam.entity.User;
import com.geralab.JavaExam.mappers.ProtoMapper;
import com.geralab.JavaExam.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@GrpcService
public class GRPCUserService extends UserServiceGrpc.UserServiceImplBase {
    private final UserRepository repository;
    private final ProtoMapper protoMapper;


    @Transactional
    @Override
    public void getUsers(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        List<User> userList = repository.findAll();
        List<com.geralab.JavaExam.User> users = userList.stream()
                .map(entity -> com.geralab.JavaExam.User.newBuilder()
                        .setId(entity.getId())
                        .setFirstName(entity.getFirstName())
                        .setAge(entity.getAge())
                        .setCountry(protoMapper.mapToProtoCountry(entity.getCountry()))
                        .build())
                .collect(Collectors.toList());
        UserResponse response = UserResponse.newBuilder().addAllUsers(users).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void addUser(AddUserRequest request, StreamObserver<AddUserResponse> responseObserver) {
        User userEntity = new User();
        userEntity.setFirstName(request.getFirstName());
        userEntity.setAge(request.getAge());
        userEntity.setCountry(protoMapper.mapFromProtoCountry(request.getCountry()));

        User savedUser = repository.save(userEntity);

        com.geralab.JavaExam.User responseUser = com.geralab.JavaExam.User.newBuilder()
                .setId(savedUser.getId())
                .setFirstName(savedUser.getFirstName())
                .setAge(savedUser.getAge())
                .setCountry(protoMapper.mapToProtoCountry(savedUser.getCountry()))
                .build();

       AddUserResponse response = AddUserResponse.newBuilder()
               .setUser(responseUser)
               .setMessage("Added successfully")
               .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void getAllUsersOlderOrEqualThanRequestAge(AdditionalUserInfoRequest request, StreamObserver<AdditionalUserInfoResponse> responseObserver) {
        List<User> userList = repository.findAllByAgeIsGreaterThanEqual(request.getAge());
        List<com.geralab.JavaExam.User> users = userList.stream()
                .map(entity -> com.geralab.JavaExam.User.newBuilder()
                        .setId(entity.getId())
                        .setFirstName(entity.getFirstName())
                        .setAge(entity.getAge())
                        .setCountry(protoMapper.mapToProtoCountry(entity.getCountry()))
                        .build())
                .collect(Collectors.toList());
        AdditionalUserInfoResponse response = AdditionalUserInfoResponse.newBuilder().addAllUsers(users).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
