package com.devonfw.application.jtqj.ownermanagement.logic.api.usecase;

import com.devonfw.application.jtqj.ownermanagement.logic.api.to.OwnerEto;

/**
 * Interface of UcManageOwner to centralize documentation and signatures of
 * methods.
 */
public interface UcManageOwner {

	/**
	 * Deletes a owner from the database by its id 'ownerId'.
	 *
	 * @param ownerId Id of the owner to delete
	 * @return boolean <code>true</code> if the owner can be deleted,
	 *         <code>false</code> otherwise
	 */
	boolean deleteOwner(long ownerId);

	/**
	 * Saves a owner and store it in the database.
	 *
	 * @param owner the {@link OwnerEto} to create.
	 * @return the new {@link OwnerEto} that has been saved with ID and version.
	 */
	OwnerEto saveOwner(OwnerEto owner);

}
