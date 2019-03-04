import 'clay-label';
import 'clay-progress-bar';
import 'clay-sticker';
import PortletBase from 'frontend-js-web/liferay/PortletBase.es';
import {openToast} from 'frontend-js-web/liferay/toast/commands/OpenToast.es';

import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './ChangeListsHistory.soy';

/**
 * Handles the tags of the selected
 * fileEntries inside a modal.
 */
class ChangeListsHistory extends PortletBase {

	created() {
		let headers = new Headers();
		headers.append('Content-Type', 'application/json');

		let init = {
			credentials: 'include',
			headers,
			method: 'GET'
		};

		let sort = '&' + this.orderByCol + ':' + this.orderByType;

		let urlProcesses = this.urlProcesses + '&type=' + this.filterStatus + '&offset=0&limit=5' + sort;

		fetch(urlProcesses, init)
			.then(r => r.json())
			.then(response => this._populateProcessEntries(response))
			.then(() => this.loaded = true)
			.catch(
				error => {
					const message = typeof error === 'string' ?
						error :
						Liferay.Util.sub(Liferay.Language.get('an-error-occured-while-getting-data-from-x'), this.urlProcesses);

					openToast(
						{
							message,
							title: Liferay.Language.get('error'),
							type: 'danger'
						}
					);
				}
			);
	}

	static _getState(processEntryStatus) {
		if (processEntryStatus === 'successful') {
			return 'published';
		}

		return processEntryStatus;
	}

	_populateProcessEntries(processEntries) {
		this.processEntries = [];

		processEntries.forEach(
			processEntry => {
				this.processEntries.push(
					{
						description: processEntry.ctcollection.description,
						name: processEntry.ctcollection.name,
						percentage: processEntry.percentage,
						state: ChangeListsHistory._getState(processEntry.status),
						timestamp: new Intl.DateTimeFormat(
							Liferay.ThemeDisplay.getBCP47LanguageId(),
							{
								day: 'numeric',
								hour: 'numeric',
								minute: 'numeric',
								month: 'numeric',
								year: 'numeric'
							}).format(new Date(processEntry.date)),
						userInitials: processEntry.userInitials,
						userName: processEntry.userName
					}
				);
			}
		);
	}

}

/**
 * State definition.
 * @ignore
 * @static
 * @type {!Object}
 */
ChangeListsHistory.STATE = {

	filterStatus: Config.string(),

	filterUser: Config.string(),

	orderByCol: Config.string(),

	orderByType: Config.string(),

	urlProcesses: Config.string(),

	processEntries: Config.arrayOf(
		Config.shapeOf(
			{
				description: Config.string(),
				name: Config.string(),
				percentage: Config.number(),
				state: Config.string(),
				timestamp: Config.string(),
				userInitials: Config.string(),
				userName: Config.string()
			}
		)
	),

	loaded: Config.bool().value(false),

	/**
	 * Path to images.
	 *
	 * @instance
	 * @memberOf ChangeListsHistory
	 * @review
	 * @type {String}
	 */
	spritemap: Config.string().required()

};

// Register component

Soy.register(ChangeListsHistory, templates);

export {ChangeListsHistory};
export default ChangeListsHistory;