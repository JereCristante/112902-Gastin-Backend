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
        Account account = accountRepository.findById(cuenta_id).orElseThrow(()-> new ResourceNotFoundException("Cuenta","id",cuenta_id));
        if(!movement.getAccount().getId().equals(cuenta_id)){
            AccountDTO updatedOldAccount = new AccountDTO();
            updatedOldAccount.setId(movement.getAccount().getId());
            updatedOldAccount.setDescription(movement.getAccount().getDescription());
            updatedOldAccount.setActive(movement.getAccount().getActive());
            if(movement.getMovementType().getId().equals(1L)){
                updatedOldAccount.setBalance(movement.getAccount().getBalance()-movement.getAmount());
            }
            if(movement.getMovementType().getId().equals(2L)){
                updatedOldAccount.setBalance(movement.getAccount().getBalance()-movement.getAmount());
            }
            accountService.updateAccount(updatedOldAccount, movement.getAccount().getId());
            AccountDTO updatedNewAccount = new AccountDTO();
            updatedNewAccount.setId(account.getId());
            updatedNewAccount.setDescription(account.getDescription());
            updatedNewAccount.setActive(account.getActive());
            if(movement.getMovementType().getId().equals(1L)){
                //movement.setAmount(movementDTO.getAmount()*-1);
                updatedNewAccount.setBalance(account.getBalance()+(movementDTO.getAmount()*-1));
                movement.setAmount(movementDTO.getAmount()*-1);
            }
            if(movement.getMovementType().getId().equals(2L)){
                updatedNewAccount.setBalance(account.getBalance()+movementDTO.getAmount());
                movement.setAmount(movementDTO.getAmount());
            }
            accountService.updateAccount(updatedNewAccount, cuenta_id);
        }else if(movement.getMovementType().getId().equals(1L) && !movement.getAmount().equals(movementDTO.getAmount()*-1)){
            AccountDTO updatedAccount = new AccountDTO();
            updatedAccount.setId(account.getId());
            updatedAccount.setDescription(account.getDescription());
            updatedAccount.setActive(account.getActive());
            updatedAccount.setBalance(account.getBalance()-movement.getAmount()-movementDTO.getAmount());
            movement.setAmount(movementDTO.getAmount()*-1);
            accountService.updateAccount(updatedAccount, cuenta_id);
        } else if (movement.getMovementType().getId().equals(2L) && !movement.getAmount().equals(movementDTO.getAmount())) {
            AccountDTO updatedAccount2 = new AccountDTO();
            updatedAccount2.setId(account.getId());
            updatedAccount2.setDescription(account.getDescription());
            updatedAccount2.setActive(account.getActive());
            updatedAccount2.setBalance(account.getBalance()-movement.getAmount()+movementDTO.getAmount());
            movement.setAmount(movementDTO.getAmount());
            accountService.updateAccount(updatedAccount2, cuenta_id);
        }else{
            if(movement.getMovementType().getId().equals(1L)){
                if(movementDTO.getAmount()>0){
                    movement.setAmount(movementDTO.getAmount()*-1);
                }else{
                    movement.setAmount(movementDTO.getAmount()*1);
                }
            }
            if(movement.getMovementType().getId().equals(2L)){
                movement.setAmount(movementDTO.getAmount());
            }
        }

        movement.setDate(movementDTO.getDate());
        movement.setDescription(movementDTO.getDescription());

        //if(cuentadestino_id!=null){
            //Account destinationAccount = accountRepository.findById(cuentadestino_id).orElseThrow(()-> new ResourceNotFoundException("CuentaDestino","id",cuentadestino_id));
            //movement.setDestinationAccount(destinationAccount);
        //}
        Category category = categoryRepository.findById(categoria_id).orElseThrow(()-> new ResourceNotFoundException("Categoria","id",categoria_id));
        movement.setCategory(category);
        movement.setAccount(account);

        Movement newMovement = movementRepository.save(movement);
        return dtoMapping(newMovement);
    }

    @Override
    public void deleteMovement(Long id) {
        Movement movement = movementRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("movimiento","id",id));
        //Account account = accountRepository.findById(movement.).orElseThrow(()-> new ResourceNotFoundException("Cuenta","id",cuenta_id));
        movement.setActive(false);
        AccountDTO updatedAccount = new AccountDTO();
        updatedAccount.setId(movement.getAccount().getId());
        updatedAccount.setDescription(movement.getAccount().getDescription());
        updatedAccount.setActive(movement.getAccount().getActive());
        if(movement.getMovementType().equals(1L)){
            updatedAccount.setBalance(movement.getAccount().getBalance()-movement.getAmount());
            accountService.updateAccount(updatedAccount, movement.getAccount().getId());
        }
        if(movement.getMovementType().equals(2L)){
            updatedAccount.setBalance(movement.getAccount().getBalance()-movement.getAmount());
            accountService.updateAccount(updatedAccount, movement.getAccount().getId());
        }
        if(movement.getMovementType().equals(3L)){
            //
        }
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
            for (ListDateMovementsDTO date:dates){
                List<ListMovementsDTO> movements = movementRepository.getMovementsListReport(usuario_id, Date.valueOf(date.getDate()));
                for (ListMovementsDTO movement:movements) {
                    Account account = accountRepository.findById(movement.getAccount()).orElseThrow(() -> new ResourceNotFoundException("Cuenta", "id", movement.getAccount()));
                    AccountDTO accountDTO = new AccountDTO();
                    accountDTO.setId(account.getId());
                    accountDTO.setDescription(account.getDescription());
                    movement.setAccountObj(accountDTO);
                     if(movement.getCategory() != null){
                         Category category = categoryRepository.findById(movement.getCategory()).orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", movement.getCategory()));
                         CategoryDTO categoryDTO = new CategoryDTO();
                         categoryDTO.setId(category.getId());
                         categoryDTO.setDescription(category.getDescription());
                         categoryDTO.setIcon(category.getIcon());
                         movement.setCategoryObj(categoryDTO);
                     }else{
                         CategoryDTO categoryDTO = new CategoryDTO();
                         categoryDTO.setIcon("swap-horizontal-outline");
                         categoryDTO.setDescription("Movimientos");
                         movement.setCategory(0L);
                         movement.setCategoryObj(categoryDTO);
                     }
                }
               date.setMovements(movements);
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
