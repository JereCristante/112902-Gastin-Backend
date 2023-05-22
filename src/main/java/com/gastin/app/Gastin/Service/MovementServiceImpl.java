package com.gastin.app.Gastin.Service;

import com.gastin.app.Gastin.DTO.*;
import com.gastin.app.Gastin.Exceptions.ResourceNotFoundException;
import com.gastin.app.Gastin.Model.*;
import com.gastin.app.Gastin.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MovementServiceImpl implements MovementService{
    @Autowired
    private MovementRepository movementRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MovementTypeRepository movementTypeRepository;
    @Autowired
    private AccountService accountService;
    @Override
    public MovementDTO createMovement(Long usuario_id, Long cuenta_id, Long cuentadestino_id, Long categoria_id, Long tipo_movimiento_id, MovementDTO movementDTO) {
        Movement movement = entityMapping(movementDTO);
        AccountDTO updatedAccount = new AccountDTO();
        User user = userRepository.findById(usuario_id).orElseThrow(()-> new ResourceNotFoundException("Usuario","id",usuario_id));
        Account account = accountRepository.findById(cuenta_id).orElseThrow(()-> new ResourceNotFoundException("Cuenta","id",cuenta_id));
        updatedAccount.setId(account.getId());
        updatedAccount.setDescription(account.getDescription());
        updatedAccount.setActive(account.getActive());
        if(categoria_id!=null) {
            Category category = categoryRepository.findById(categoria_id).orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", categoria_id));
            movement.setCategory(category);
        }
        MovementType movementType = movementTypeRepository.findById(tipo_movimiento_id).orElseThrow(()-> new ResourceNotFoundException("TipoMovimiento","id",tipo_movimiento_id));
        movement.setUser(user);
        movement.setAccount(account);
        movement.setMovementType(movementType);
        if(cuentadestino_id!=null){
            Account destinationAccount = accountRepository.findById(cuentadestino_id).orElseThrow(()-> new ResourceNotFoundException("CuentaDestino","id",cuentadestino_id));
            //movement.setDestinationAccount(destinationAccount);
            AccountDTO updatedDestAccount = new AccountDTO();
            if(tipo_movimiento_id.equals(3L)){
                //ajusta los saldos de las cuentas
                Movement movement2 = entityMapping(movementDTO);
                movement2.setAmount((-1*movement.getAmount()));
                movement2.setUser(user);
                movement2.setAccount(destinationAccount);
                movement2.setMovementType(movementType);
                updatedAccount.setBalance(account.getBalance()+movement2.getAmount());
                Movement transferMov = movementRepository.save(movement2);
                updatedDestAccount.setId(destinationAccount.getId());
                updatedDestAccount.setBalance(destinationAccount.getBalance()+movement.getAmount());
                updatedDestAccount.setDescription(destinationAccount.getDescription());
                updatedDestAccount.setActive(destinationAccount.getActive());
                accountService.updateAccount(updatedDestAccount, cuentadestino_id);
            }
        }
        //descuenta o incrementa saldo de la cuenta
        if(tipo_movimiento_id.equals(1L)){
            movement.setAmount(movement.getAmount()*-1);
            updatedAccount.setBalance(account.getBalance()+movement.getAmount());
        }
        if(tipo_movimiento_id.equals(2L)){
            updatedAccount.setBalance(account.getBalance()+movement.getAmount());
        }
        accountService.updateAccount(updatedAccount, cuenta_id);
        Movement newMovement = movementRepository.save(movement);
        return dtoMapping(newMovement);
    }

    @Override
    public MovementDTO updateMovement(Long cuenta_id, Long cuentadestino_id, Long categoria_id, MovementDTO movementDTO, Long id) {
        Movement movement = movementRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("movimiento","id",id));
        movement.setDate(movementDTO.getDate());
        movement.setAmount(movementDTO.getAmount());
        movement.setDescription(movementDTO.getDescription());
        //User user = userRepository.findById(usuario_id).orElseThrow(()-> new ResourceNotFoundException("Usuario","id",usuario_id));
        Account account = accountRepository.findById(cuenta_id).orElseThrow(()-> new ResourceNotFoundException("Cuenta","id",cuenta_id));
        if(cuentadestino_id!=null){
            Account destinationAccount = accountRepository.findById(cuentadestino_id).orElseThrow(()-> new ResourceNotFoundException("CuentaDestino","id",cuentadestino_id));
            movement.setDestinationAccount(destinationAccount);
        }
        Category category = categoryRepository.findById(categoria_id).orElseThrow(()-> new ResourceNotFoundException("Categoria","id",categoria_id));
        //MovementType movementType = movementTypeRepository.findById(tipo_movimiento_id).orElseThrow(()-> new ResourceNotFoundException("TipoMovimiento","id",tipo_movimiento_id));
        //movement.setUser(user);
        movement.setCategory(category);
        movement.setAccount(account);
        //movement.setMovementType(movementType);
        Movement newMovement = movementRepository.save(movement);
        return dtoMapping(newMovement);
    }

    @Override
    public void deleteMovement(Long id) {
        Movement movement = movementRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("movimiento","id",id));
        movement.setActive(false);
        movementRepository.save(movement);
    }
    @Override
    public List<ListDateMovementsDTO> getMovementListByUser(Long usuario_id) {
        List<ListDateMovementsDTO> dates = movementRepository.generateReport(usuario_id);
        //List<ListDateMovementsDTO> dates = new ArrayList<>();

        //if(!results.isEmpty()){
          //  for (Object[] result : results) {
            //    ListDateMovementsDTO date = new ListDateMovementsDTO();
              //  date.setDate((String) result[0]);
                //date.setTotal((Double) result[1]);
          //  }
        //}
        if(!dates.isEmpty()){
            for (ListDateMovementsDTO date:dates) {
               date.setMovements(movementRepository.getMovementsListReport(usuario_id, Date.valueOf(date.getDate())));
            }
        }

        //List<Movement> allMovements = movementRepository.findByUserId(usuario_id);
        //List<Movement> filteredCategories = allMovements.stream().filter(movement -> category.getMovementType().getId().equals(tipo_movimiento_id)).collect(Collectors.toList());
        return dates;
    }
    private MovementDTO dtoMapping(Movement movement){
        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setId(movement.getId());
        movementDTO.setDate(movement.getDate());
        movementDTO.setAmount(movement.getAmount());
        movementDTO.setActive(movement.getActive());
        movementDTO.setDescription(movement.getDescription());
        return movementDTO;
    }
    private Movement entityMapping(MovementDTO movementDTO){
        Movement movement = new Movement();
        movement.setId(movementDTO.getId());
        movement.setDate(movementDTO.getDate());
        movement.setAmount(movementDTO.getAmount());
        movement.setActive(movementDTO.getActive());
        movement.setDescription(movementDTO.getDescription());
        return movement;
    }


}
