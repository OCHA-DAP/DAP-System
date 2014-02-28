<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--
    two parameters need to be defined when including this jsp:
        - "item" the current item for which the translation component is being rendered
        -"collection" the object for the collection that's currently iterated
-->


<div ng-controller="TranslationsCtrl">
    <table class="table table-bordered table-hover table-condensed">
        <tr style="font-weight: bold">
            <td style="width: 20%"><a href="" ng-click="t_predicate='code'; t_reverse=!t_reverse">Language</a></td>
            <td style="width: 55%"><a href="" ng-click="t_predicate='value'; t_reverse=!t_reverse">Translation</a></td>
            <td style="width: 25%">Action</td>
        </tr>
        <tr ng-repeat="translation in ${param.item}.translations | orderBy:t_predicate:t_reverse">
            <td>
                <!-- non editable language --> <span e-name="language" e-form="t_rowform"> {{ translation.code }} </span>
            </td>
            <td>
                <!-- editable value --> <span editable-text="translation.value" e-class="form-control" e-name="value" e-form="t_rowform"> {{ translation.value }} </span>
            </td>
            <td style="white-space: nowrap">
                <!-- t form -->
                <form editable-form name="t_rowform"
                      onbeforesave="updateTranslation($data, ${param.item}.text_id, translation.code, ${param.item}, '${param.item}', ${param.item}.id)"
                      ng-show="t_rowform.$visible" class="form-buttons form-inline" shown="inserted == translation">
                    <button type="submit" ng-disabled="t_rowform.$waiting" class="btn btn-primary btn-xs btn-custom-default">Save</button>
                    <button type="button" ng-disabled="t_rowform.$waiting" ng-click="t_rowform.$cancel()"
                            class="btn btn-default btn-xs btn-custom-cancel">Cancel</button>
                </form>
                <div class="buttons" ng-show="!t_rowform.$visible">
                    <button class="btn btn-primary btn-xs btn-custom-default" ng-click="t_rowform.$show()">Edit</button>
                    <button class="btn btn-danger btn-xs btn-custom-danger"
                            ng-click="deleteTranslation(${param.item}.text_id, translation.code, ${param.item}, '${param.item}', ${param.item}.id)">Delete</button>
                </div>
            </td>
        </tr>
        <tr ng-show="showAddTranslation(${param.collection}, 'id', ${param.item}.id)">
            <td><select class="form-control" ng-model="newTranslation.language"
                        ng-options="language.code for language in languages | filter:languagesByAvailableTranslations(${param.collection}, 'id', ${param.item}.id)"
                        ng-class="default">
            </select></td>
            <td><input type="text" class="form-control" placeholder="Translation" id="newTranslation_{{${param.item}.text_id}}_value"
                       ng-model="newTranslation.value" required /></td>
            <td><button class="btn btn-primary btn-xs btn-custom-default"
                        ng-click="createTranslation(${param.item}.text_id, ${param.item}, '${param.item}', ${param.item}.id)">Add</button></td>
        </tr>
    </table>
    <div ng-show="addLanguage" class="translations_complete">
							<span>
								All translations complete. Do you wish to <a href="${ctx}/admin/misc/languages/">add another language</a> ?
							</span>
    </div>
</div>