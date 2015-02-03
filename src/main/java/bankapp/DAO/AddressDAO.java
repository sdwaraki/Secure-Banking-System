package bankapp.DAO;

import bankapp.Objects.Address;
import bankapp.Objects.BankUser;

public interface AddressDAO {
	public Address getAddressbyAddressId(String addressID);
	public String addAddress(BankUser a);
	public void deleteAddressbyAddressId(String addressID);
	public Address getAddressbyUserId(String userId);
	public void updateAddress(Address a);
}
