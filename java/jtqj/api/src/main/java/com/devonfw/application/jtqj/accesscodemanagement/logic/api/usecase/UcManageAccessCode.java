package com.devonfw.application.jtqj.accesscodemanagement.logic.api.usecase;

import java.util.List;

import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeEto;

/**
 * Interface of UcManageAccessCode to centralize documentation and signatures of
 * methods.
 */
public interface UcManageAccessCode {

	/**
	 * Deletes a accessCode from the database by its id 'accessCodeId'.
	 *
	 * @param accessCodeId Id of the accessCode to delete
	 * @return boolean <code>true</code> if the accessCode can be deleted,
	 *         <code>false</code> otherwise
	 */
	boolean deleteAccessCode(long accessCodeId);

	/**
	 * Saves a accessCode and store it in the database.
	 *
	 * @param accessCode the {@link AccessCodeEto} to create.
	 * @return the new {@link AccessCodeEto} that has been saved with ID and
	 *         version.
	 */
	AccessCodeEto saveAccessCode(AccessCodeEto accessCode);

	/**
	 * Updates all accesCodes when a queue starts
	 *
	 * @param List of accessCodes related to such queue the {@link AccessCodeEto} to update.
	 * @return void
	 */
	void updateCodesOnStartQueue(long queueId);

	/**
	 * Updates the current code and next code if available
	 *
	 * @return the next {@link AccessCodeEto} if available, else Eto will be empty
	 */
	AccessCodeEto callNextCode();
}
