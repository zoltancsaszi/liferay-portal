/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import {PortletBase, openToast} from 'frontend-js-web';
import {dom} from 'metal-dom';
import {EventHandler} from 'metal-events';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './ChangeListsIndicator.soy';

const BLUE_BACKGROUND_TOOLTIP_CSS_CLASS_NAME = 'tooltip-background-blue';

const CHANGE_LISTS_INDICATOR_QUERY_SELECTOR = '[data-change-lists-indicator]';

const GREEN_BACKGROUND_TOOLTIP_CSS_CLASS_NAME = 'tooltip-background-green';

const PRODUCTION_COLLECTION_ID = 0;

const TOOLTIP_QUERY_SELECTOR = '.yui3-widget.tooltip';

/**
 * Component for the Change Lists indicator
 * @review
 */
class ChangeListsIndicator extends PortletBase {
	/**
	 * @inheritDoc
	 */
	created() {
		this._eventHandler = new EventHandler();
		const urlActiveCollection =
			this.urlCollectionsBase +
			'?companyId=' +
			Liferay.ThemeDisplay.getCompanyId() +
			'&userId=' +
			Liferay.ThemeDisplay.getUserId() +
			'&collectionType=active';

		this._render(urlActiveCollection);

		const instance = this;

		Liferay.on('refreshChangeTrackingIndicator', function() {
			instance._render(urlActiveCollection);
		});
	}

	/**
	 * @inheritDoc
	 */
	detached() {
		this._eventHandler.removeAllListeners();
	}

	/**
	 * @inheritDoc
	 */
	disposed() {
		this._eventHandler.removeAllListeners();
	}

	/**
	 * Check element in document.
	 * @memberof ChangeListsIndicator
	 * @param selector
	 * @private
	 */
	_checkElement(selector) {
		const element = document.querySelector(selector);

		var result = Promise.resolve(element);

		if (element === null) {
			result = this._rafAsync().then(() => this._checkElement(selector));
		}

		return result;
	}

	/**
	 * Add tooltip css class.
	 * @memberof ChangeListsIndicator
	 * @param cssClassName
	 * @private
	 */
	_addTooltipCssClass(cssClassName) {
		this._checkElement(TOOLTIP_QUERY_SELECTOR).then(element => {
			if (element && !element.classList.contains(cssClassName)) {
				element.classList.add(cssClassName);
			}
		});
	}

	/**
	 * Check element opacity.
	 * @memberof ChangeListsIndicator
	 * @param selector
	 * @private
	 */
	_checkElementHidden(selector) {
		const element = document.querySelector(selector);

		var result = Promise.resolve(element);

		if (element && this._getStyle(element, 'display') != 'none') {
			result = this._rafAsync().then(() =>
				this._checkElementHidden(selector)
			);
		}

		return result;
	}

	/**
	 * Handles mouseenter event.
	 * @memberof ChangeListsIndicator
	 * @param {!Event} event
	 * @private
	 */
	_getDataRequest(url, callback) {
		const headers = new Headers();
		headers.append('Content-Type', 'application/json');
		headers.append('X-CSRF-Token', Liferay.authToken);

		const type = 'GET';

		const init = {
			credentials: 'include',
			headers,
			method: type
		};

		fetch(url, init)
			.then(response => response.json())
			.then(response => callback(response))
			.catch(error => {
				const message =
					typeof error === 'string'
						? error
						: Liferay.Language.get(
								'an-error-occured-while-getting-the-active-change-list'
						  );

				openToast({
					message,
					title: Liferay.Language.get('error'),
					type: 'danger'
				});
			});
	}

	/**
	 * Get style of the element.
	 * @memberof ChangeListsIndicator
	 * @param element, property
	 * @private
	 */
	_getStyle(element, property) {
		var result = null;

		if (element && element.currentStyle) {
			result = element.currentStyle[property];
		} else if (window.getComputedStyle) {
			result = window
				.getComputedStyle(element, null)
				.getPropertyValue(property);
		}

		return result;
	}

	/**
	 * Handles change list indicator blur event.
	 * @memberof ChangeListsIndicator
	 * @private
	 */
	_handleChangeListIndicatorBlur() {
		this._handleChangeListIndicatorMouseLeave.bind(this);
	}

	/**
	 * Handles change list indicator focus event.
	 * @memberof ChangeListsIndicator
	 * @private
	 */
	_handleChangeListIndicatorFocus() {
		this._handleChangeListIndicatorMouseEnter.bind(this);
	}

	/**
	 * Handles change list indicator click event.
	 * @memberof ChangeListsIndicator
	 * @private
	 */
	_handleChangeListIndicatorMouseClick() {
		this._handleChangeListIndicatorMouseEnter.bind(this);
	}

	/**
	 * Handles change list indicator mouseenter event.
	 * @memberof ChangeListsIndicator
	 * @private
	 */
	_handleChangeListIndicatorMouseEnter() {
		this._addTooltipCssClass(this._tooltipCssClassName);
	}

	/**
	 * Handles change list indicator mouseleave events.
	 * @memberof ChangeListsIndicator
	 * @private
	 */
	_handleChangeListIndicatorMouseLeave() {
		this._removeTooltipCssClass(this._tooltipCssClassName);
	}

	/**
	 * Handles tooltip mouseleave events.
	 * @memberof ChangeListsIndicator
	 * @private
	 */
	_handleTooltipMouseLeave() {
		this._removeTooltipCssClass(this._tooltipCssClassName);
	}

	/**
	 * Async requestAnimationFrame returning Promise.
	 * @memberof ChangeListsIndicator
	 * @param
	 * @private
	 */
	_rafAsync() {
		return new Promise(resolve => {
			requestAnimationFrame(resolve);
		});
	}

	/**
	 * Remove tooltip css class.
	 * @memberof ChangeListsIndicator
	 * @param cssClassName
	 * @private
	 */
	_removeTooltipCssClass(cssClassName) {
		this._checkElementHidden(TOOLTIP_QUERY_SELECTOR).then(element => {
			if (element && element.classList.contains(cssClassName)) {
				element.classList.remove(cssClassName);
			}
		});
	}

	/**
	 * Rerenders the screen.
	 * @param urlActiveCollection
	 * @private
	 */
	_render(urlActiveCollection) {
		this._getDataRequest(urlActiveCollection, response => {
			if (response) {
				this.activeChangeListId = response.items[0].id;
				this.activeChangeListName = response.items[0].name;
				this._setTooltipCssClassName(this.activeChangeListId);
				this._setEventHandlers();
			}
		});
	}

	/**
	 * Set the event handlers for the change lists indicator.
	 * @memberof ChangeListsIndicator
	 * @param selector
	 * @private
	 */
	_setChangeListIndicatorEventHandler(selector) {
		this._eventHandler.add(
			dom.delegate(
				document,
				'blur',
				selector,
				this._handleChangeListIndicatorBlur.bind(this)
			),
			dom.delegate(
				document,
				'click',
				selector,
				this._handleChangeListIndicatorMouseClick.bind(this)
			),
			dom.delegate(
				document,
				'focus',
				selector,
				this._handleChangeListIndicatorFocus.bind(this)
			),
			dom.delegate(
				document,
				'mouseenter',
				selector,
				this._handleChangeListIndicatorMouseEnter.bind(this)
			),
			dom.delegate(
				document,
				'mouseleave',
				selector,
				this._handleChangeListIndicatorMouseLeave.bind(this)
			)
		);
	}

	/**
	 * Set the event handlers.
	 * @memberof ChangeListsIndicator
	 * @param {!Event} event
	 * @private
	 */
	_setEventHandlers() {
		this._setChangeListIndicatorEventHandler(
			CHANGE_LISTS_INDICATOR_QUERY_SELECTOR
		);
		this._seTooltipEventHandlers(TOOLTIP_QUERY_SELECTOR);
	}

	/**
	 * Set the tooltip css class name.
	 * @memberof ChangeListsIndicator
	 * @param {!Event} event
	 * @private
	 */
	_setTooltipCssClassName(activeChangeListId) {
		this._tooltipCssClassName =
			activeChangeListId !== PRODUCTION_COLLECTION_ID
				? BLUE_BACKGROUND_TOOLTIP_CSS_CLASS_NAME
				: GREEN_BACKGROUND_TOOLTIP_CSS_CLASS_NAME;
	}

	/**
	 * Set the event handlers for the tooltip.
	 * @memberof ChangeListsIndicator
	 * @param selector
	 * @private
	 */
	_seTooltipEventHandlers(selector) {
		this._eventHandler.add(
			dom.delegate(
				document,
				'mouseleave',
				selector,
				this._handleTooltipMouseLeave.bind(this)
			)
		);
	}
}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
ChangeListsIndicator.STATE = {
	/**
	 * Name of the tooltip css class.
	 * @default
	 * @instance
	 * @memberOf ChangeListsIndicator
	 * @review
	 * @type {!string}
	 */

	_tooltipCssClassName: Config.string().value(''),

	/**
	 * Id of the active change list.
	 * @default
	 * @instance
	 * @memberOf ChangeListsIndicator
	 * @review
	 * @type {!string}
	 */

	activeChangeListId: Config.number(),

	/**
	 * Name of the active change list.
	 * @default
	 * @instance
	 * @memberOf ChangeListsIndicator
	 * @review
	 * @type {!string}
	 */

	activeChangeListName: Config.string().value(''),

	/**
	 * Id of production collection.
	 * @default
	 * @instance
	 * @memberOf ChangeListsIndicator
	 * @review
	 * @type {!string}
	 */

	productionCollectionId: Config.number().value(PRODUCTION_COLLECTION_ID),

	/**
	 * Path of the available icons.
	 * @default undefined
	 * @instance
	 * @memberOf ChangeListsIndicator
	 * @review
	 * @type {!string}
	 */

	spritemap: Config.string().required(),

	/**
	 * Base REST API URL to collection resource
	 * @default
	 * @instance
	 * @memberOf ChangeListsIndicator
	 * @review
	 * @type {string}
	 */

	urlCollectionsBase: Config.string()
};

Soy.register(ChangeListsIndicator, templates);

export default ChangeListsIndicator;
