package com.example.demo.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import org.springframework.stereotype.Service;


import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.todoRepository;





@Service
public class TaskService {
	@Autowired
	todoRepository res;
	public void save(Task task) {
		res.save(task);
		
	}
	public List<Task> listAll() {
		// TODO Auto-generated method stub
		List<Task> task= new ArrayList<Task>();
		res.findAll().forEach(t1 ->task.add(t1) );
		return task;
		
	}
	public Task get(long id) {
		// TODO Auto-generated method stub
		return res.findById((long) id).get();
	}
	public void delete(int id) {
		// TODO Auto-generated method stub
			res.deleteById((long) id);
	}
	public void saveuser(User user) {
		// TODO Auto-generated method stub
		
	}
	public Task check(String title) {
		Task task=res.findByTitle(title);
		return task;
	}
	public List<Task> listtask(String title) {
		// TODO Auto-generated method stub
		List<Task> task= new ArrayList<Task>();
		task.add(res.findByTitle(title));
		return task;
	}
	public List<Task> listtask(long id) {
		// TODO Auto-generated method stub
		List<Task> task= new ArrayList<Task>();
			task.add(get(id));
		
		return task;
	}
	
	public List<Task> listall() {
        return res.findAll();
    }
	public Optional<Task> checkid(long id) {
		// TODO Auto-generated method stub
		Optional<Task> task=res.findById(id);
		return task;
	}
	public Page<Task> findPaginated(int pageNo,int pageSize){
		Pageable pageable=PageRequest.of(pageNo-1,pageSize);
		return this.res.findAll(pageable);
	}
	public Page<Task> findsorted(int pageNo, int pageSize) {
		Sort sort =Sort.by("title").ascending();
			 Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
			 
			 return this.res.findAll(pageable);
			}

	
	
}